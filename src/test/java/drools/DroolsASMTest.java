package drools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.mvel2.asm.ClassReader;
import org.mvel2.asm.ClassWriter;

import droolsasm.MyClassVisitor;

public class DroolsASMTest {

	@Test
	public void test() throws Exception {
		FileInputStream is = new FileInputStream("target/classes/my/DroolsPojo.class");

        
		ClassReader cr = new ClassReader(is);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MyClassVisitor cv = new MyClassVisitor(cw);
        cr.accept(cv, 0);

        new File("target/ASM/classes/my/").mkdirs();
        FileOutputStream fos = new FileOutputStream("target/ASM/classes/my/DroolsPojo.class");
        fos.write(cw.toByteArray());
        fos.close(); 
	}
}
