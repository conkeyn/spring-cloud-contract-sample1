import org.springframework.cloud.contract.spec.Contract

def contractDsl = Contract.make {
    description "发送消息到verifications队列中"
    // 在consumer端会用此label名称去触发发送一条消息。
    label 'input_ouput_label'
    //作为producer端契约时，用于触发能够输出消息的接口。
    input {
        triggeredBy("triggeMessageFromSourceStub()")
    }
    //作为producer端契约时，用于验证producer是的数据结构是否正确；
    //作为consumber端的契约存根时，用于模拟输出与producer一致的消息体；
    outputMessage{
        sentTo "verifications1"
        body(
                eligible:true,
                content :"tranform from middle serivce.",
                receivedCount:value(consumer(1),producer(regex("\\d+"))),
                rejectedCount:value(consumer(1),producer(regex("\\d+"))),
        )
        headers{
            header("contentType",value(consumer("application/json;charset=UTF-8"),producer(regex("application/json.*"))))
        }
    }
}