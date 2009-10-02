package migool.poster.cms.ucoz.post;

import java.util.Date;
import java.util.List;

import migool.post.internal.Image;

/**
 * 
 * @author Denis Migol
 *
 */
public class BoardUcozPost {
	public String cat;
	public int kind; // 1 - info, 2 - demand (spros), 3 - supply (predlozhenie)
	public String brief;
	public String message;
	public List<Image> files;
	public String aname;
	public String aemail;
	public String asite;
	public String phone;
	public Date pa; // pya, pma, pda - razmestit' do
	public List<String> others;
}
