package asm;
import org.objectweb.asm.*;
import util.Utilities.*;


public class WhileLoop {

	public static void main(String[] args) {
		
		//ClassWriter
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "WhileLoop", null, "java/lang/Object", null);
		
		//MethodVistor -- Constructor
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}//end method  visitor (constructor)
		
		
		/*
		 * This is a While Loop that uses i as a counter and prints i as it goes through the loop. Once i reaches 10, the loop stops.
		 */
		
		//MethodVisitor -- While Loop
		{
			Label label1 = new Label();  //label that the code jumps to if i reaches 10
			Label label2 = new Label(); //label that redirects code to run through the loop again
			
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			
			mv.visitCode();
			
			mv.visitInsn(Opcodes.ICONST_0); //sets counter to 0
            mv.visitVarInsn(Opcodes.ISTORE, 1); //stores value in index 1

            mv.visitLabel(label2); //code jumps to this label to run through the loop again
            mv.visitVarInsn(Opcodes.ILOAD, 1); //loads first value
            mv.visitIntInsn(Opcodes.BIPUSH, 10); //sets the condition of the while loop so that it ends after i reaches 10
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, label1); //if the condition is not valid anymore, it jumps to label 1 which stops the loop
            mv.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(Opcodes.ILOAD,1); //loads value
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); //prints each value as it goes through loop
            mv.visitIincInsn(1,1); //increments through the loop
            mv.visitJumpInsn(Opcodes.GOTO, label2); //the code jumps to this label to run through the loop again


            mv.visitLabel(label1); //code jumps to this to end loop
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
		}
		
		//write method visitors to byte
	       cw.visitEnd();

	        byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"WhileLoop.class");
	        
	        System.out.println("Done!");
		

	}//end main

}//end WhileLoop
