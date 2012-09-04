package org.ubimix.pageset.loaders;

import org.ubimix.commons.uri.Uri;
import org.ubimix.commons.xml.XmlException;
import org.ubimix.commons.xml.XmlWrapper;
import org.ubimix.pageset.IUrlTransformer;
import org.ubimix.pageset.PageSetConfig;
import org.ubimix.pageset.UrlToPathMapper;

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
