import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label 'bookAddedEvent'
    input {
        //contract si triggered by a method
        triggeredBy('bookAddedEventTrigger()')
    }
    outputMessage {
        //destination to which the output message will be sent, in the demo - topic exchange
        sentTo('book-events')
        headers {
            //messagingContentType(applicationJson()) TODO: test me
            header('Content-Type', 'application/json; charset=UTF-8')
        }
        body(
                '''{
                "isbn": "9780091956141",
                "id": "aa1dc09f-7b64-4e7e-a6f6-7eb50dcd6e9d",
                "bookId": "9bf258be-19d4-4338-b172-60a1b7ef076b"
                }'''
        )
    }
}