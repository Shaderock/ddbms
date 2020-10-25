package services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RMIServiceType {
    MESSAGE(1),
    SEQUENCE_GENERATOR(2),
    USER(3);

    @Getter
    private final int portIncrement;
}
