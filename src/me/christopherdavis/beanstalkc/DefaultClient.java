// This file is part of me.christopherdavis.beanstalkc
// Copyright: (c) 2014 Christopher Davis <http://christopherdavis.me>
// License: MIT http://opensource.org/licenses/MIT

package me.christopherdavis.beanstalkc;

import me.christopherdavis.beanstalkc.adapter.SocketAdapter;
import me.christopherdavis.beanstalkc.command.PutJobCommand;
import me.christopherdavis.beanstalkc.command.UseTubeCommand;
import me.christopherdavis.beanstalkc.command.ReserveCommand;
import me.christopherdavis.beanstalkc.command.ReserveTimeoutCommand;
import me.christopherdavis.beanstalkc.command.DeleteCommand;
import me.christopherdavis.beanstalkc.command.ReleaseCommand;

/**
 * The default implementation of Client.
 *
 * @since   0.1
 * @author  Christopher Davis <http://christopherdavis.me>
 */
public class DefaultClient implements Client
{
    final private static int DEFAULT_DELAY = 0;
    final private static int DEFAULT_TTR = 60; // 1 minute
    final private static int DEFAULT_PRIORITY = 1024;

    private Adapter adapter;

    public DefaultClient(Adapter adapter) throws BeanstalkcException
    {
        this.adapter = adapter;
    }

    /**
     * @see     Client#put
     */
    public Job put(int priority, int delay, int ttr, byte[] data) throws BeanstalkcException
    {
        return doCommand(new PutJobCommand(priority, delay, ttr, data));
    }

    /**
     * @see     Client#put
     */
    public Job put(byte[] data) throws BeanstalkcException
    {
        return put(DEFAULT_PRIORITY, DEFAULT_DELAY, DEFAULT_TTR, data);
    }

    /**
     * @see     Client#use
     */
    public boolean use(String tube) throws BeanstalkcException
    {
        return doCommand(new UseTubeCommand(tube));
    }

    /**
     * @see     Client#reserve
     */
    public Job reserve() throws BeanstalkcException
    {
        return doCommand(new ReserveCommand());
    }

    /**
     * @see     Client#reserve
     */
    public Job reserve(int timeout) throws BeanstalkcException
    {
        return doCommand(new ReserveTimeoutCommand(timeout));
    }


    /**
     * @see     Client#delete
     */
    public boolean delete(Job job) throws BeanstalkcException
    {
        return delete(job.getId());
    }


    /**
     * @see     Client#delete
     */
    public boolean delete(int job_id) throws BeanstalkcException
    {
        return doCommand(new DeleteCommand(job_id));
    }

    /**
     * @see     Client#release
     */
    public boolean release(Job job, int priority, int delay) throws BeanstalkcException
    {
        return release(job.getId(), priority, delay);
    }

    /**
     * @see     Client#release
     */
    public boolean release(int job_id, int priority, int delay) throws BeanstalkcException
    {
        return doCommand(new ReleaseCommand(job_id, priority, delay));
    }

    /**
     * @see     Client#release
     */
    public boolean release(Job job) throws BeanstalkcException
    {
        return release(job.getId(), DEFAULT_PRIORITY, DEFAULT_DELAY);
    }

    /**
     * @see     Client#release
     */
    public boolean release(int job_id) throws BeanstalkcException
    {
        return release(job_id, DEFAULT_PRIORITY, DEFAULT_DELAY);
    }

    /**
     * @see     Client#bury
     */
    public boolean bury(Job job, int priority) throws BeanstalkcException
    {
        return false;
    }


    /**
     * @see     Client#bury
     */
    public boolean bury(int job_id, int priority) throws BeanstalkcException
    {
        return false;
    }

    /**
     * @see     Client#bury
     */
    public boolean bury(Job job) throws BeanstalkcException
    {
        return false;
    }

    /**
     * @see     Client#bury
     */
    public boolean bury(int job_id) throws BeanstalkcException
    {
        return false;
    }

    /**
     * @see     Client#bury
     */
    public boolean touch(Job job) throws BeanstalkcException
    {
        return false;
    }

    /**
     * @see     Client#touch
     */
    public boolean touch(int job_id) throws BeanstalkcException
    {
        return false;
    }

    /**
     * @see     Client#touch
     */
    public int watch(String tube) throws BeanstalkcException
    {
        return 0;
    }

    /**
     * @see     Client#ignore
     */
    public int ignore(String tube) throws BeanstalkcException
    {
        return 0;
    }

    private <T> T doCommand(Command<T> cmd) throws BeanstalkcException
    {
        return adapter.perform(cmd);
    }
}
