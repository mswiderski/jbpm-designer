package org.jbpm.designer.repository;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.URIException;
import org.jbpm.designer.util.Base64Backport;

public class UriUtils {

    private static final String URL_ENCODED_REGEX = ".*%\\d{1,}.*";

    public static String encode(String value) {
        if(value == null) {
            return value;
        }
        if (value.matches(URL_ENCODED_REGEX)) {
            return value;
        }
        try {
            return org.apache.commons.httpclient.util.URIUtil.encodePath(value);
        } catch (URIException e) {
            throw new IllegalArgumentException("Invalid value " + value + " given, error: " + e.getMessage(), e);
        }
    }

    public static String decode(String value) {
        if(value == null) {
            return value;
        }
        try {
            return org.apache.commons.httpclient.util.URIUtil.decode(value);
        } catch (URIException e) {
            throw new IllegalArgumentException("Invalid value " + value + " given, error: " + e.getMessage(), e);
        }

    }

    public static String base64Decode(String uniqueId) {
        if (uniqueId != null && Base64Backport.isBase64(uniqueId)) {
            byte[] decoded = Base64.decodeBase64(uniqueId);
            try {
                String uri = new String(decoded, "UTF-8");

                return uri;
            } catch (UnsupportedEncodingException e) {

            }
        }

        return uniqueId;
    }
}
