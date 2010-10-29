package migool.http;

/**
 * @author Denis Migol
 * 
 */
public final class HttpRequestHeader {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private HttpRequestHeader() {
	}

	/**
	 * Content-Types that are acceptable
	 */
	public static final String ACCEPT = "Accept";

	/**
	 * Character sets that are acceptable
	 */
	public static final String ACCEPT_CHARSET = "Accept-Charset";

	/**
	 * Acceptable encodings
	 */
	public static final String ACCEPT_ENCODING = "Accept-Encoding";

	/**
	 * Acceptable languages for response
	 */
	public static final String ACCEPT_LANGUAGE = "Accept-Language";

	/**
	 * Allows the server to indicate its acceptance of range requests for a
	 * resource
	 */
	public static final String ACCEPT_RANGES = "Accept-Ranges";

	/**
	 * Authentication credentials for HTTP authentication
	 */
	public static final String AUTHORIZATION = "Authorization";

	/**
	 * Used to specify directives that MUST be obeyed by all caching mechanisms
	 * along the request/response chain
	 */
	public static final String CACHE_CONTROL = "Cache-Control";

	/**
	 * What type of connection the user-agent would prefer
	 */
	public static final String CONNECTION = "Connection";

	/**
	 * an HTTP cookie previously sent by the server with Set-Cookie
	 */
	public static final String COOKIE = "Cookie";

	/**
	 * The length of the request body in octets (8-bit bytes)
	 */
	public static final String CONTENT_LENGTH = "Content-Length";

	/**
	 * The mime type of the body of the request (used with POST and PUT
	 * requests)
	 */
	public static final String CONTENT_TYPE = "Content-Type";

	/**
	 * The date and time that the message was sent
	 */
	public static final String DATE = "Date";

	/**
	 * Indicates that particular server behaviors are required by the client
	 */
	public static final String EXPECT = "Expect";

	/**
	 * The email address of the user making the request
	 */
	public static final String FROM = "From";

	/**
	 * The domain name of the server (for virtual hosting), mandatory since
	 * HTTP/1.1
	 */
	public static final String HOST = "Host";

	/**
	 * Only perform the action if the client supplied entity matches the same
	 * entity on the server. This is mainly for methods like PUT to only update
	 * a resource if it has not been modified since the user last updated it.
	 */
	public static final String IF_MATCH = "If-Match";

	/**
	 * Allows a 304 Not Modified to be returned if content is unchanged
	 */
	public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

	/**
	 * Allows a 304 Not Modified to be returned if content is unchanged
	 */
	public static final String IF_NONE_MATCH = "If-None-Match";

	/**
	 * If the entity is unchanged, send me the part(s) that I am missing;
	 * otherwise, send me the entire new entity
	 */
	public static final String IF_RANGE = "If-Range";

	/**
	 * Only send the response if the entity has not been modified since a
	 * specific time.
	 */
	public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

	/**
	 * Limit the number of times the message can be forwarded through proxies or
	 * gateways.
	 */
	public static final String MAX_FORWARDS = "Max-Forwards";

	/**
	 * Implementation-specific headers that may have various effects anywhere
	 * along the request-response chain.
	 */
	public static final String PRAGMA = "Pragma";

	/**
	 * Authorization credentials for connecting to a proxy.
	 */
	public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";

	/**
	 * Request only part of an entity. Bytes are numbered from 0.
	 */
	public static final String RANGE = "Range";

	/**
	 * This is the address of the previous web page from which a link to the
	 * currently requested page was followed.
	 */
	public static final String REFERER = "Referer";

	/**
	 * The transfer encodings the user agent is willing to accept: the same
	 * values as for the response header Transfer-Encoding can be used, plus the
	 * "trailers" value (related to the "chunked" transfer method) to notify the
	 * server it accepts to receive additional headers (the trailers) after the
	 * last, zero-sized, chunk.
	 */
	public static final String TE = "TE";

	/**
	 * Ask the server to upgrade to another protocol.
	 */
	public static final String UPGRADE = "Upgrade";

	/**
	 * The user agent string of the user agent
	 */
	public static final String USER_AGENT = "User-Agent";

	/**
	 * Informs the server of proxies through which the request was sent.
	 */
	public static final String VIA = "Via";

	/**
	 * A general warning about possible problems with the entity body.
	 */
	public static final String WARNING = "Warning";
}
