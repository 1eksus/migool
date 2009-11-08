package migool.ot.client.serializable;

import java.io.Serializable;

/**
 * 
 * @author Denis Migol
 * 
 */
@SuppressWarnings("serial")
public class GrabHostConfig implements Serializable {

	public String host;
	public int grabCount;

	public String lastPage;
	public String lastId;
}
