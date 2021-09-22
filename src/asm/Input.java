package asm;
import org.objectweb.asm.*;
import util.Utilities.*;

public class Input implements Opcodes {
    
    public static void main(String[] args) {
        
    	//Classwriter
        ClassWriter cw = new ClassWriter(0);
        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "UserInput", null, "java/lang/Object", null);
        
        {
        	//Method Visitor constructor
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        
        {
        	
            MethodVisitor  mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/util/Scanner"); //Declaring Scanner
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitVarInsn(ASTORE, 1); //Store scanner
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Enter your name: "); //Prompt for use input
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false); //Print out prompt for use input
            mv.visitVarInsn(ALOAD, 1); //Load scanner
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "nextLine", "()Ljava/lang/String;", false); //Take user input
            mv.visitVarInsn(ASTORE, 2); //Store user input
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); 
            mv.visitTypeInsn(NEW, "java/lang/StringBuilder"); //Creates StringBuilder
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("My name is "); //String to be put before user's name
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false); 
            mv.visitVarInsn(ALOAD, 2); //Load user input
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); //Print String and user input
            mv.visitVarInsn(ALOAD, 1); //Load Scanner
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", "close", "()V", false); //Close scanner
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        util.Utilities.writeFile(b,"Input.class");
        
        System.out.println("Done!");
	
 
    }
}