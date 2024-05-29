package se.main.springboothtmxbankapplication.adapter.out.hibernate.transaction.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.main.springboothtmxbankapplication.domain.transaction.primitive.TransactionType;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionTypeConverterTest {

    private final TransactionTypeConverter converter = new TransactionTypeConverter();

    @ParameterizedTest
    @MethodSource("transactionTypeToId")
    void toDbId(TransactionType type, int dbId) {
        int result = converter.toDbId(type);
        assertThat(result).isEqualTo(dbId);
    }

    @ParameterizedTest
    @MethodSource("transactionTypeToId")
    void toTransactionType(TransactionType type, int dbId) {
        TransactionType result = converter.toTransactionType(dbId);
        assertThat(result).isEqualTo(type);
    }

    static Stream<Arguments> transactionTypeToId() {
        return Stream.of(
                Arguments.of(TransactionType.INCOMING, 0),
                Arguments.of(TransactionType.OUTGOING, 1)
        );
    }

}