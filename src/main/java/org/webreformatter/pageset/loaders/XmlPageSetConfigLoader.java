package org.webreformatter.pageset.loaders;

import org.webreformatter.commons.uri.Uri;
import org.webreformatter.commons.xml.XmlException;
import org.webreformatter.commons.xml.XmlWrapper;
import org.webreformatter.pageset.IUrlTransformer;
import org.webreformatter.pageset.PageSetConfig;
import org.webreformatter.pageset.UrlToPathMapper;

/**
 * This class is used to configure {@link CompositeSiteConfig} instances using
 * XML configurations.
 * 
 * @author kotelnikov
 */
public class XmlPageSetConfigLoader extends AbstractXmlLoader {

    public XmlPageSetConfigLoader() {
    }

    public void configureSites(
        PageSetConfig.Builder config,
        Uri configUrl,
        XmlWrapper xml) throws XmlException {
        XmlUrlToPathMapperLoader loader = new XmlUrlToPathMapperLoader();
        final UrlToPathMapper urlToPathMapper = loader
            .configure(configUrl, xml);
        config.setUrlToPathMapper(urlToPathMapper);

        IUrlTransformer downloadUrlTransformer = new IUrlTransformer() {
            public Uri transform(Uri uri) {
                return uri;
            }
        };
        IUrlTransformer localizeUrlTransformer = new IUrlTransformer() {
            public Uri transform(Uri uri) {
                Uri result = urlToPathMapper.uriToPath(uri);
                if (result == null) {
                    result = uri;
                }
                return result;
            }
        };
        config
            .setDownloadUrlTransformer(downloadUrlTransformer)
            .setLocalizeUrlTransformer(localizeUrlTransformer);
    }

}
