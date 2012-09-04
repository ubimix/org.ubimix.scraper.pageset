/**
 * 
 */
package org.ubimix.pageset;

import org.ubimix.commons.uri.Uri;

/**
 * @author kotelnikov
 */
public interface IUrlTransformer {

    IUrlTransformer EMPTY = new IUrlTransformer() {
        public Uri transform(Uri uri) {
            return uri;
        }
    };

    Uri transform(Uri uri);
}
