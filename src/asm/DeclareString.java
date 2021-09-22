package asm;

import org.objectweb.asm.*;
import util.Utilities.*;

public class DeclareString {

	public static void main(String[] args) {
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "DeclareString", null, "java/lang/Object", null);
		
		//Method Visitor -- Constructor
		{
			MethodVisitor mv =  cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
			
		}
		
		//MethodVisitor -- Declare String
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitLdcInsn((String)"Hello World"); //Declaires "Hello World" as a string
			mv.visitVarInsn(Opcodes.ASTORE, 1); //Stores the String into index 1 on stack
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); //introduces print
			mv.visitVarInsn(Opcodes.ALOAD, 1); //loads string from stack
			mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); //prints string
			
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();	
			System.out.println("Hello Back!");
		}

	      cw.visitEnd();

	        byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"DeclareString.class");
	        
	        System.out.println("Done!");
		
	}//end main

}//end DeclareString
