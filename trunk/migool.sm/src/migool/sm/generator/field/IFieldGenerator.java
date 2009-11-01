package migool.sm.generator.field;

import migool.sm.generator.IGenerator;

/**
 * 
 * @author Denis Migol
 *
 */
public interface IFieldGenerator extends IGenerator {
	byte[] next();
}
