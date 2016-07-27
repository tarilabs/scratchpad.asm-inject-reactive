package myasm;

import org.mvel2.asm.MethodVisitor;
import org.mvel2.asm.Opcodes;

public class MyMethodVisitor  extends MethodVisitor implements Opcodes {

    public MyMethodVisitor(final MethodVisitor mv) {
        super(ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        /* TODO: System.err.println("CALL" + name); */
  
        /* do call */
        mv.visitMethodInsn(opcode, owner, name, desc, itf);

        /* TODO: System.err.println("RETURN" + name);  */
    }
}