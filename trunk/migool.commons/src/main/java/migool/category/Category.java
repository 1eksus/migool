package migool.category;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Denis Migol
 * 
 */
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3582620165989197210L;

	private String name;
	private Set<String> synonyms;
	private Set<Category> subCategories;
	private Category parent;

	/**
	 * 
	 * @author Denis Migol
	 * 
	 */
	public static final class Builder {
		/**
		 * 
		 * @return
		 */
		public static final Builder newBuilder() {
			return new Builder();
		}

		private Category category;

		public Builder() {
			category = new Category();
		}

		public Builder setName(final String name) {
			category.setName(name);
			return this;
		}

		public Builder setSynonyms(final Set<String> synonyms) {
			category.setSynonyms(synonyms);
			return this;
		}

		private Set<String> getSynonyms() {
			Set<String> ret = category.getSynonyms();
			if (ret == null) {
				category.setSynonyms(ret = new HashSet<String>());
			}
			return ret;
		}

		public Builder addSynonym(final String synonym) {
			getSynonyms().add(synonym);
			return this;
		}

		public Builder addSynonyms(final Collection<String> synonyms) {
			getSynonyms().addAll(synonyms);
			return this;
		}

		public Builder setSubCategories(final Set<Category> subCategories) {
			category.setSubCategories(subCategories);
			return this;
		}

		private Set<Category> getSubCategories() {
			Set<Category> ret = category.getSubCategories();
			if (ret == null) {
				category.setSubCategories(ret = new HashSet<Category>());
			}
			return ret;
		}

		public Builder addSubCategory(final Category category) {
			getSubCategories().add(category);
			return this;
		}

		public Builder addSubCategories(final Set<Category> subCategories) {
			getSubCategories().addAll(subCategories);
			return this;
		}

		public Builder setParent(final Category parent) {
			category.setParent(parent);
			return this;
		}

		public Category build() {
			return category;
		}
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param synonyms
	 *            the synonyms to set
	 */
	public void setSynonyms(Set<String> synonyms) {
		this.synonyms = synonyms;
	}

	/**
	 * @return the synonyms
	 */
	public Set<String> getSynonyms() {
		return synonyms;
	}

	/**
	 * @param subCategories
	 *            the subCategories to set
	 */
	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

	/**
	 * @return the subCategories
	 */
	public Set<Category> getSubCategories() {
		return subCategories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		// result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((parent == null || parent.name == null) ? 0 : parent.name.hashCode());
		result = prime * result + ((subCategories == null) ? 0 : subCategories.hashCode());
		result = prime * result + ((synonyms == null) ? 0 : synonyms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Category)) {
			return false;
		}
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		if (subCategories == null) {
			if (other.subCategories != null) {
				return false;
			}
		} else if (!subCategories.equals(other.subCategories)) {
			return false;
		}
		if (synonyms == null) {
			if (other.synonyms != null) {
				return false;
			}
		} else if (!synonyms.equals(other.synonyms)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", synonyms=" + synonyms + ", subCategories=" + subCategories + "]";
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

}
