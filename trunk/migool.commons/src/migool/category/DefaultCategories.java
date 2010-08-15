package migool.category;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class DefaultCategories {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private DefaultCategories() {
	}

	public static final Category OTHER;
	private static final Set<Category> cats;

	private static Category createSoft() {
		final Category softOS = createSoftOS();
		final Category soft = Category.Builder.newBuilder().setName("soft").addSynonym("soft").addSynonym("software")
				.addSynonym("софт").addSynonym("program").addSynonym("programs").addSynonym("программы").addSynonym(
						"progs").addSynonym("проги").addSynonym("warez").addSynonym("варез").addSynonym("система")
				.addSubCategory(softOS).build();
		softOS.setParent(soft);
		return soft;
	}

	private static Category createSoftOS() {
		return Category.Builder.newBuilder().setName("os").addSynonym("os").addSynonym("ос и сборки").addSynonym(
				"windows").build();
	}

	private static Category createMovies() {
		return Category.Builder.newBuilder().setName("movies").addSynonym("movie").addSynonym("film").addSynonym(
				"фильм").addSynonym("video").addSynonym("видео").addSynonym("смотреть").addSynonym("мультфильм")
				.addSynonyms(createMoviesGenresSynonyms()).build();
	}

	private static Collection<String> createMoviesGenresSynonyms() {
		return Arrays.asList("боевик", "документальн", "драм", "историческ", "комедия", "мелодрам", "приключени",
				"триллер", "ужас", "фантастик", "фэнтези");
	}

	private static Category createSerials() {
		return Category.Builder.newBuilder().setName("serials").addSynonym("serial").addSynonym("сериал").addSynonym(
				"многосерийн").build();
	}

	private static Category createTV() {
		return Category.Builder.newBuilder().setName("tv").addSynonym("tv").addSynonym("тв").addSynonym("передачи")
				.addSynonym("передач").build();
	}

	private static Category createMusic() {
		return Category.Builder.newBuilder().setName("music").addSynonym("music").addSynonym("музыка")
				.addSynonym("mp3").addSynonym("слушать").addSynonyms(createMusicGenresSynonyms()).build();
	}

	private static Collection<String> createMusicGenresSynonyms() {
		return Arrays.asList("саундтрэк", "саундртрек", "house", "rap", "chillout", "trance", "dance", "pop", "rock",
				"metal", "шансон");
	}

	private static Category createGames() {
		return Category.Builder.newBuilder().setName("games").addSynonym("game").addSynonym("игр").build();
	}

	private static Category createBooks() {
		return Category.Builder.newBuilder().setName("books").addSynonym("books").addSynonym("e-books").addSynonym(
				"книг").addSynonym("журнал").addSynonym("e-book").addSynonym("book").addSynonym("чтиво").addSynonym(
				"читать").addSynonym("листать").build();
	}

	private static Category createImages() {
		return Category.Builder.newBuilder().setName("images").addSynonym("image").addSynonym("изображени").addSynonym(
				"picture").addSynonym("картинки").addSynonym("wallpaper").addSynonym("обои").addSynonym("design")
				.addSynonym("дизайн").build();
	}

	private static Category createCelebrity() {
		return Category.Builder.newBuilder().setName("celebrity").addSynonym("celebrity").addSynonym("знаменитост")
				.addSynonym("star").addSynonym("звезд").addSynonym("звёзд").build();
	}

	private static Category createHumour() {
		return Category.Builder.newBuilder().setName("humour").addSynonym("humour").addSynonym("humor").addSynonym(
				"юмор").addSynonym("pricol").addSynonym("прикол").addSynonym("funny").addSynonym("смешно").addSynonym(
				"ржачно").addSynonym("ржать").addSynonym("ржач").addSynonym("смех").addSynonym("смеяться").addSynonym(
				"улыбнуло").addSynonym("угар").build();
	}

	private static Category createMobile() {
		return Category.Builder.newBuilder().setName("mobile").addSynonym("mobile").addSynonym("мобил").addSynonym(
				"мобильник").addSynonym("для смартфонов и кпк").addSynonym("для смартфонов").addSynonym("кпк")
				.addSynonym("смартфон").build();
	}

	private static Category createPSP() {
		return Category.Builder.newBuilder().setName("psp").addSynonym("psp").addSynonym("psp2").addSynonym("psp3")
				.build();
	}

	private static Category createOther() {
		return Category.Builder.newBuilder().setName("other").addSynonym("other").addSynonym("разное").addSynonym(
				"прочее").addSynonym("прочие").addSynonym("всякое").addSynonym("всячина").addSynonym("different")
				.build();
	}

	static {
		OTHER = createOther();
		cats = Category.Builder.newBuilder().addSubCategory(createSoft()).addSubCategory(createMovies())
				.addSubCategory(createSerials()).addSubCategory(createTV()).addSubCategory(createMusic())
				.addSubCategory(createGames()).addSubCategory(createBooks()).addSubCategory(createImages())
				.addSubCategory(createCelebrity()).addSubCategory(createHumour()).addSubCategory(createMobile())
				.addSubCategory(createPSP()).addSubCategory(OTHER).build().getSubCategories();
	}

	/**
	 * 
	 * @return
	 */
	public static Set<Category> get() {
		return cats;
	}

	public static void main(String[] args) {
		System.out.println(get());
	}
}
