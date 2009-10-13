package migool.poster.cms.ucoz.post;

import java.util.List;

import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public class PublUcozPost {
	public String ocat;
	public String title;
	public String brief;
	public boolean format_brief = true;
	public String message;
	public boolean format_message = true;
	public List<Image> files;
	public String aname;
	public String aemail;
	public String asite;
	public String source;
	public boolean is_pending = false;
	public boolean coms_allowed = true;
}
