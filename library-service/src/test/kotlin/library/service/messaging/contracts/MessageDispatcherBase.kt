package library.service.messaging.contracts

import library.service.business.books.domain.BookRecord
import library.service.business.books.domain.events.BookAdded
import library.service.business.books.domain.states.Available
import library.service.business.books.domain.states.BookState
import library.service.business.books.domain.types.BookId
import library.service.messaging.MessagingBookEventDispatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier
import utils.Books.THE_MARTIAN
import utils.classification.ContractTest
import java.time.OffsetDateTime
import java.util.*

@ContractTest
@SpringBootTest
@AutoConfigureMessageVerifier
abstract class MessageDispatcherBase {

    @Autowired
    private lateinit var messagingBookEventDispatcher: MessagingBookEventDispatcher
    /**
     * This function is defined in contract "triggeredBy" section and is responsible for creating and sending the event
     * that is specified inside the contract. In our case - book added event.
     */
    fun bookAddedEventTrigger(){
    //Prepare test data for book added event
        val newBook = BookAdded(
            id = UUID.fromString("aa1dc09f-7b64-4e7e-a6f6-7eb50dcd6e9d"),
            timestamp = OffsetDateTime.now(),
            bookRecord = BookRecord(
                 id = BookId(UUID.fromString("9bf258be-19d4-4338-b172-60a1b7ef076b")),
                book = THE_MARTIAN,
                state = Available
            )
        )
        //send message
        messagingBookEventDispatcher.dispatch(newBook)
    }

}