package org.oracle.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandReaderTest {

    @Mock
    private InputStream mockStream;

    @Test
    public void getNextCommand_should_read_the_command_from_inputStream() {
        InputStream stream = new ByteArrayInputStream("advance\r\n5\r\n".getBytes(StandardCharsets.UTF_8));
        CommandReader commandReader = new CommandReader(stream);
        assertThat(commandReader.getNextCommand(), equalTo("advance"));
        assertThat(commandReader.getNextCommand(), equalTo("5"));
    }

    @Test
    public void close_the_input_stream()
            throws IOException {
        CommandReader commandReader = new CommandReader(mockStream);
        commandReader.close();
        verify(mockStream, times(1)).close();
    }
}
