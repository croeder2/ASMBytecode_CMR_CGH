package asm;

import org.objectweb.asm.*;
import util.Utilities.*;

public class Accumulator {

	public static void main(String[] args) {
		
		//ClassWriter
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "DeclareString", null, "java/lang/Object", null);
		
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
		 * This is a While Loop that uses i as a counter. It takes input from the Scanner as an integer, and then sums each input 
		 * until  i reaches 10.
		 */
		//Method Visitor
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
		
			Label label1 = new Label(); //label that the code jumps to in order to loop through again
			Label label2 = new Label(); //label that the code jumps to if the conditions of the loop are invalid (end of the loop)
			
			
			mv.visitCode();
			
			mv.visitLabel(label1); //label1 is where the code jumps to to go through the loop again
			mv.visitInsn(Opcodes.ICONST_0); //sets sum variable to 0
			mv.visitVarInsn(Opcodes.ISTORE, 1); //stores value in index 1
			mv.visitInsn(Opcodes.ICONST_0); //sets counter variable to 0
			mv.visitVarInsn(Opcodes.ISTORE, 2); //stores value in index 2
			mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner"); //introduces/creates Scanner
			mv.visitInsn(Opcodes.DUP); 
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream"); //this introduces the scanner input
			
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false); //introduces scanner
			
			mv.visitVarInsn(Opcodes.ASTORE, 3); //stores the variable in index 2
			mv.visitVarInsn(Opcodes.ILOAD,2);
			mv.visitIntInsn(Opcodes.BIPUSH, 10);
			mv.visitJumpInsn(Opcodes.IF_ICMPGE, label2); //if the value is greater than or equal to 10, the code jumps to label 2 which ends the loop
			mv.visitVarInsn(Opcodes.ALOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt()", "()I", false); //takes input from scanner
			mv.visitVarInsn(Opcodes.ISTORE, 3); //stores input in index 3
			mv.visitVarInsn(Opcodes.ILOAD, 1); //values are loaded from the stack and added together
			mv.visitVarInsn(Opcodes.ILOAD, 3);
			mv.visitInsn(Opcodes.IADD); //values added
			mv.visitVarInsn(Opcodes.ISTORE, 1); //sum is stored
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream"); 
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); //sum is printed
			mv.visitIincInsn(4,1); //incremented through
			mv.visitJumpInsn(Opcodes.GOTO, label1); //the loop restarts as the condition is still met
			
			mv.visitLabel(label2); //code goes to here if the value is greater than 10
			
			
			mv.visitInsn(Opcodes.RETURN);
	        mv.visitMaxs(1, 1);
	        mv.visitEnd();
			
		}//end Method Visitor -- Accumulator
		
		//write method visitors to byte
	       cw.visitEnd();

	        byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"Accumulator.class");
	        
	        System.out.println("Done!");
	}//end main

}// end Accumulator.java
