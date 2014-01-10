// This file is part of me.christopherdavis.beanstalkc
// Copyright: (c) 2014 Christopher Davis <http://christopherdavis.me>
// License: MIT http://opensource.org/licenses/MIT

package me.christopherdavis.beanstalkc.command;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import me.christopherdavis.beanstalkc.BeanstalkcException;

public class ReserveCommandTest
{
    private ByteArrayInputStream input;
    private ByteArrayOutputStream output;

    @Test
    public void testReserveInput() throws Exception
    {
        ReserveCommand cmd = new ReserveCommand();

        cmd.execute(input, output);

        Assert.assertArrayEquals(
            "reserve\r\n".getBytes(),
            output.toByteArray()
        );
    }

    @Before
    public void setUp()
    {
        input = new ByteArrayInputStream("RESERVED 1 4\r\ntest\r\n".getBytes());
        output = new ByteArrayOutputStream();
    }
}
