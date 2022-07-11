package com.edu.queue;

import java.util.LinkedHashMap;

public class MsgQueue {
    private int size;
    private int seqNo;

    // id - (status, msg)
    private LinkedHashMap<String, Message> hashMsg; 

    public MsgQueue(int size)
    {
        this.size = size;
        this.seqNo = 0;
        hashMsg = new LinkedHashMap<String, Message>();
    }

    public String MsgEnqueue(String msg)
    {
        if (hashMsg.size() == size)
            return "Queue Full";
        
        String messageId = "ID_" + Thread.currentThread().getId() + seqNo++;

        Message msgMap = new Message(messageId, msg);
        msgMap.setStatus("A"); // status : available
        hashMsg.put(messageId, msgMap);

        return "Enqueued";
    }

    public String MsgDequeue()
    {
        if (hashMsg.size() == 0)
            return "Queue Empty";

        String key = hashMsg.keySet().iterator().next();
        
        String res = hashMsg.get(key).getMessage() + "(" + key + ")";

        hashMsg.remove(key);

        return res;
    }
    
    public Message dequeue()
    {
        if (hashMsg.size() == 0)
            return null;

        String key = hashMsg.keySet().iterator().next();
        
        Message res = hashMsg.get(key);

        hashMsg.remove(key);

        return res;
    }

    public String MsgGet()
    {
        if (hashMsg.size() > 0)
            for(String key : hashMsg.keySet())
            {
                if (hashMsg.get(key).getStatus().equals("A"))
                {
                	Message val = hashMsg.get(key);
                	val.setStatus("U");
                    hashMsg.put(key, val); 
                    return val.getMessage();
                }
            }

        return "No Msg";
    }

    public String MsgSet(int msgid)
    {
        if (hashMsg.size() > 0)
        {
            if (hashMsg.containsKey(msgid))
            {
                hashMsg.get(msgid).setStatus("A");  
                return "Msg Set";
            }
        }

        return "Set Fail";
    }

    public String MsgDel(int id)
    {
        if (hashMsg.size() > 0)
        {
            if (hashMsg.containsKey(id))
            {
                hashMsg.remove(id);
                return "Deleted";
            }
        }

        return "Not Deleted";
    }
}
