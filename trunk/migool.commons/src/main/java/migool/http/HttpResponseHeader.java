package migool.http;

/**
 * @author Denis Migol
 * 
 */
public final class HttpResponseHeader {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private HttpResponseHeader() {
	}

	/**
	 * What partial content range types this server supports
	 */
	public static final String ACCEPT_RANGES = "Accept-Ranges";

	/**
	 * The age the object has been in a proxy cache in seconds
	 */
	public static final String AGE = "Age";

	/**
	 * Valid actions for a specified resource. To be used for a 405 Method not
	 * allowed
	 */
	public static final String ALLOW = "Allow";

	/**
	 * Tells all caching mechanisms from server to client whether they may cache
	 * this object
	 */
	public static final String CACHE_CONTROL = "Cache-Control";

	/**
	 * The type of encoding used on the data
	 */
	public static final String CONTENT_ENCODING = "Content-Encoding";

	/**
	 * The language the content is in
	 */
	public static final String CONTENT_LANGUAGE = "Content-Language";

	/**
	 * The length of the response body in octets (8-bit bytes)
	 */
	public static final String CONTENT_LENGTH = "Content-Length";

	/**
	 * An alternate location for the returned data
	 */
	public static final String CONTENT_LOCATION = "Content-Location";

	/**
	 * An opportunity to raise a "File Download" dialogue box for a known MIME
	 * type
	 */
	public static final String CONTENT_DISPOSITION = "Content-Disposition";

	/**
	 * A Base64-encoded binary MD5 sum of the content of the response
	 */
	public static final String CONTENT_MD5 = "Content-MD5";

	/**
	 * Where in a full body message this partial message belongs
	 */
	public static final String CONTENT_RANGE = "Content-Range";

	/**
	 * The mime type of this content
	 */
	public static final String CONTENT_TYPE = "Content-Type";

	/**
	 * The date and time that the message was sent
	 */
	public static final String DATE = "Date";

	/**
	 * An identifier for a specific version of a resource, often a Message
	 * Digest
	 */
	public static final String ETAG = "ETag";

	/**
	 * Gives the date/time after which the response is considered stale
	 */
	public static final String EXPIRES = "Expires";

	/**
	 * The last modified date for the requested object, in RFC 2822 format
	 */
	public static final String LAST_MODIFIED = "Last-Modified";

	/**
	 * Used in redirection, or when a new resource has been created
	 */
	public static final String LOCATION = "Location";

	/**
	 * Implementation-specific headers that may have various effects anywhere
	 * along the request-response chain
	 */
	public static final String PRAGMA = "Pragma";

	/**
	 * Request authentication to access the proxy
	 */
	public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";

	/**
	 * Used in redirection, or when a new resource has been created (This is a
	 * proprietary/non-standard header extension introduced by Netscape and
	 * supported by most web browsers)
	 */
	public static final String REFRESH = "Refresh";

	/**
	 * If an entity is temporarily unavailable, this instructs the client to try
	 * again after a specified period of time
	 */
	public static final String RETRY_AFTER = "Retry-After";

	/**
	 * A name for the server
	 */
	public static final String SERVER = "Server";

	/**
	 * an HTTP cookie
	 */
	public static final String SET_COOKIE = "Set-Cookie";

	/**
	 * The Trailer general field value indicates that the given set of header
	 * fields is present in the trailer of a message encoded with chunked
	 * transfer-coding
	 */
	public static final String TRAILER = "Trailer";

	/**
	 * The form of encoding used to safely transfer the entity to the user.
	 * Currently defined methods are: chunked, compress, deflate, gzip, identity
	 */
	public static final String TRANSFER_ENCODING = "Transfer-Encoding";

	/**
	 * Tells downstream proxies how to match future request headers to decide
	 * whether the cached response can be used rather than requesting a fresh
	 * one from the origin server
	 */
	public static final String VARY = "Vary";

	/**
	 * Informs the client of proxies through which the response was sent
	 */
	public static final String VIA = "Via";

	/**
	 * A general warning about possible problems with the entity body
	 */
	public static final String WARNING = "Warning";

	/**
	 * Indicates the authentication scheme that should be used to access the
	 * requested entity
	 */
	public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
}
