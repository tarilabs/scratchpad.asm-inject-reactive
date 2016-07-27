package droolsasm;

import org.mvel2.asm.Label;
import org.mvel2.asm.MethodVisitor;
import org.mvel2.asm.Opcodes;

public class MyMethodVisitor  extends MethodVisitor implements Opcodes {

    public MyMethodVisitor(final MethodVisitor mv) {
        super(ASM5, mv);
    }
    
	@Override
	public void visitEnd() {
		System.err.println("custom");
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESTATIC, "org/drools/core/phreak/ReactiveObjectUtil", "notifyModification", "(Lorg/drools/core/phreak/ReactiveObject;)V", false);
		super.visitEnd();
	}
   
}