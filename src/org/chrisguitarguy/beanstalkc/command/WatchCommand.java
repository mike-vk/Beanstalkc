// Copyright: (c) 2014 Christopher Davis <http://christopherdavis.me>
// License: MIT http://opensource.org/licenses/MIT

package org.chrisguitarguy.beanstalkc.command;

import java.io.OutputStream;
import java.io.IOException;
import org.chrisguitarguy.beanstalkc.BeanstalkcException;

/**
 * Issue a watch command to the server.
 *
 * @since   0.1
 * @author  Christopher Davis <http://christopherdavis.me>
 */
public class WatchCommand extends AbstractWatchingCommand
{
    private String tube;

    public WatchCommand(String tube)
    {
        this.tube = tube;
    }

    /**
     * @see     AbstractCommand#sendRequest
     */
    @Override
    public void sendRequest(OutputStream out) throws BeanstalkcException, IOException
    {
        out.write(String.format(
            "watch %s\r\n",
            tube
        ).getBytes());
    }
}
