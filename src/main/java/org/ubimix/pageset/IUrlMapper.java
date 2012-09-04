package org.ubimix.pageset;

import org.ubimix.commons.uri.Uri;

/**
 * @author kotelnikov
 */
public interface IUrlMapper {

    /**
     * Returns an URI of the specified type associated with the given URI
     * 
     * @param type the type of the URL
     * @param uri the URL used to detect the base URL
     * @return an URI of the specified type associated with the given URI
     */
    Uri getUrl(String type, Uri uri);

}