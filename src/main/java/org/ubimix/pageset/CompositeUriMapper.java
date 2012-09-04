/**
 * 
 */
package org.ubimix.pageset;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.ubimix.commons.uri.Uri;

/**
 * @author kotelnikov
 */
public class CompositeUriMapper implements IUrlMapper {

    private List<IUrlMapper> fList = new CopyOnWriteArrayList<IUrlMapper>();

    /**
     * 
     */
    public CompositeUriMapper() {
    }

    public void addUriMapper(IUrlMapper mapper) {
        fList.add(mapper);
    }

    /**
     * @see org.ubimix.pageset.IUrlMapper#getUrl(java.lang.String,
     *      org.ubimix.commons.uri.Uri)
     */
    public Uri getUrl(String type, Uri uri) {
        Uri result = null;
        for (IUrlMapper mapper : fList) {
            result = mapper.getUrl(type, uri);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public void removeUriMapper(IUrlMapper mapper) {
        fList.remove(mapper);
    }
}
