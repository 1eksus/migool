package migool.category;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import migool.util.StringUtil;

/**
 * 
 * @author Denis Migol
 * 
 */
public final class CategoryUtil {
	/**
	 * Don't let anyone instantiate this class.
	 */
	private CategoryUtil() {
	}

	private static final String OTHER_FIND = (String) DefaultCategories.OTHER.getSynonyms().toArray()[0];

	/**
	 * 
	 * @param category
	 * @param wheres
	 * @param find
	 * @return
	 */
	public static String getCategoryName(final Category category, final Collection<String> wheres, final String find) {
		final String findLower = find.toLowerCase();
		for (final String where : wheres) {
			if (where.toLowerCase().contains(findLower)) {
				return where;
			}
		}
		final Set<String> synonyms = category.getSynonyms();
		if (synonyms.contains(findLower) || StringUtil.contains(synonyms, findLower) != null
				|| StringUtil.contains(findLower, synonyms) != null) {
			// equals
			for (final String where : wheres) {
				final String whereLower = where.toLowerCase();
				if (synonyms.contains(whereLower)) {
					return where;
				}
			}
			// contains
			for (final String where : wheres) {
				final String whereLower = where.toLowerCase();
				if (StringUtil.contains(whereLower, synonyms) != null) {
					return where;
				}
			}
			final Category parent = category.getParent();
			if (parent != null) {
				return getCategoryName(parent, wheres, parent.getSynonyms().toArray()[0].toString());
			}
		} else {
			final Set<Category> subCategories = category.getSubCategories();
			if (subCategories != null && subCategories.size() > 0) {
				for (final Category subCategory : subCategories) {
					final String ret = getCategoryName(subCategory, wheres, find);
					if (ret != null) {
						return ret;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param categories
	 * @param where
	 * @param find
	 * @return
	 */
	public static String getCategoryName(final Collection<Category> categories, final Collection<String> wheres,
			final String find) {
		for (final Category subCategory : categories) {
			final String ret = getCategoryName(subCategory, wheres, find);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	public static String getCategoryName(final Collection<String> wheres, final String find) {
		String ret = getCategoryName(DefaultCategories.get(), wheres, find);
		if (ret == null) {
			ret = getCategoryName(DefaultCategories.get(), wheres, OTHER_FIND);
		}
		return ret;
	}

	/**
	 * 
	 * @param categories
	 * @param wheres
	 * @param finds
	 * @return
	 */
	public static Set<String> getCategoryNames(final Collection<Category> categories, final Collection<String> wheres,
			final Collection<String> finds) {
		final Set<String> ret = new HashSet<String>();
		for (final String find : finds) {
			final String where = getCategoryName(categories, wheres, find.toLowerCase());
			if (where != null) {
				ret.add(where);
			}
		}
		return ret;
	}

	public static Set<String> getCategoryNames(final Collection<String> wheres, final Collection<String> finds) {
		final Collection<Category> categories = DefaultCategories.get();
		final Set<String> ret = getCategoryNames(categories, wheres, finds);
		if (ret.isEmpty()) {
			ret.add(getCategoryName(categories, wheres, OTHER_FIND));
		}
		return ret;
	}

	public static void main(String[] args) {
		Set<String> wheres = new HashSet<String>(Arrays.asList("фильмы", "поиграть", "разное", "музыка", "программы",
				"чтиво"));

		System.out.println(getCategoryName(wheres, "ос"));
		System.out.println(getCategoryName(wheres, "игры"));
		System.out.println(getCategoryName(wheres, "почитать"));
		System.out.println(getCategoryName(wheres, "asdf"));
		System.out.println(getCategoryName(wheres, "прог"));

		System.out.println();
		wheres = new HashSet<String>(Arrays.asList("Фильмы", "Программы", "Сериалы", "Музыка", "Игры", "Чтиво",
				"Знаменитости", "Обои", "Приколы", "Разное"));

		System.out.println(getCategoryName(wheres, "Сборки Windows"));

		System.out.println();
		wheres = new HashSet<String>(Arrays.asList("Софт", "Видео", "Сериалы", "Послушать", "Поиграть", "Почитать",
				"Звезды", "Обои", "Приколы", "Прочее"));

		System.out.println(getCategoryName(wheres, "Сборки Windows"));
	}
}
