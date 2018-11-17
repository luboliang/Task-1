package ptteng.util;
import ptteng.model.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
        public static byte[] serialize(Object object) {
            //ObjectOutputStream oos = null;
           // ByteArrayOutputStream baos = null;
            try {
                // 序列化
                ByteArrayOutputStream   baos = new ByteArrayOutputStream();

                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                byte[] bytes = baos.toByteArray();
                return bytes;
            } catch (Exception e) {

            }
            return null;
        }

    public static Object unserizlize(byte[] byt){
        //ObjectInputStream oii=null;
        //ByteArrayInputStream bis=null;
        ByteArrayInputStream bis=new ByteArrayInputStream(byt);
        try {
            ObjectInputStream  oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}
public static void main(String arg[]){
    Person person = new Person();
    person.setAge(18);
    byte[] serialize = serialize(person);
    for(int i=0;i<serialize.length;i++){
        System.out.println(serialize[i]);
    }
    System.out.println(serialize);
    Object deserialize = unserizlize(serialize);
    System.out.println(deserialize);
}
}
