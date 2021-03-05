import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionInfo<A> {


    private String className;
    private String constructors[];
    private String fields[];
    private String methods[];

    public ReflectionInfo(Class<A> myClass) {

        Constructor constructors[] = myClass.getConstructors();
        this.constructors = new String[constructors.length];
        for (int i = 0; i < constructors.length; i++) {
            this.constructors[i] = constructors[i].toString();
        }

        Field fields[] = myClass.getFields();
        this.fields = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            this.fields[i] = fields[i].toString();
        }

        Method methods[] = myClass.getMethods();
        this.methods = new String[methods.length];
        for (int i = 0; i < methods.length; i++) {
            this.methods[i] = methods[i].toString();
        }

        className = myClass.getSimpleName();

    }

    public void printToPrintSteam(PrintStream ps) {
        String name = "ClassName ='" + className + '\'';
        ps.println(name);
        ps.println("Constructors:");
        for (int i = 0; i < constructors.length; i++) {
            ps.println(constructors[i]);
        }
        ps.println("Fields:");
        for (int i = 0; i < fields.length; i++) {
            ps.println(fields[i]);
        }
        ps.println("Methods:");
        for (int i = 0; i < methods.length; i++) {
            ps.println(methods[i]);
        }

    }

    public Object callMethod(A object, String methodName, Object[] args) {
        try {
            Class<?> c1 = object.getClass();
            Method method = c1.getMethod(methodName, Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new));
            return method.invoke(object, args);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }


    }


    @Override
    public String toString() {
        return "Info\n" +
                "className = '" + className + '\'' +
                "\nConstructors = " + Arrays.toString(constructors) +
                "\nfields=" + Arrays.toString(fields) +
                "\nmethods=" + Arrays.toString(methods);
    }
}
