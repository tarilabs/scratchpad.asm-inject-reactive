package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.mvel2.asm.ClassReader;
import org.mvel2.asm.ClassWriter;

import myasm.MyClassVisitor;

public class ASMTest {

	@Test
	public void test() throws Exception {
		FileInputStream is = new FileInputStream("target/classes/my/MyPojo.class");

        
		ClassReader cr = new ClassReader(is);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MyClassVisitor cv = new MyClassVisitor(cw);
        cr.accept(cv, 0);

        FileOutputStream fos = new FileOutputStream("Ciao.class");
        fos.write(cw.toByteArray());
        fos.close(); 
	}
}
