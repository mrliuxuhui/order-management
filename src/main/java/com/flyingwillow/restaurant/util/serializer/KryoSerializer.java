package com.flyingwillow.restaurant.util.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by liuxuhui on 2017/7/17.
 */
public class KryoSerializer<T> implements RedisSerializer<T> {

    private static Logger logger = LogManager.getLogger(KryoSerializer.class);

    public static final byte[] EMPTY_ARRAY = new byte[0];

    private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            return kryo;
        };
    };

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return EMPTY_ARRAY;
        }
        Kryo kryo = kryos.get();
        Output output = new Output(64, -1);

        try {
            kryo.writeClassAndObject(output, t);
            return output.toBytes();
        } finally {
            closeOutputStream(output);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        Kryo kryo = kryos.get();
        Input input = null;
        try {
            input = new Input(bytes);
            return (T) kryo.readClassAndObject(input);
        } finally {
            closeInputStream(input);
        }
    }

    private static void closeOutputStream(OutputStream output) {
        if (output != null) {
            try {
                output.flush();
                output.close();
            } catch (Exception e) {
                logger.error("serialize object close outputStream exception", e);
            }
        }
    }


    private static void closeInputStream(InputStream input) {
        if (input != null) {
            try {
                input.close();
            } catch (Exception e) {
                logger.error("serialize object close inputStream exception", e);
            }
        }
    }

    private static boolean isEmpty(byte[] data){
        return (data == null || data.length == 0);
    }
}
