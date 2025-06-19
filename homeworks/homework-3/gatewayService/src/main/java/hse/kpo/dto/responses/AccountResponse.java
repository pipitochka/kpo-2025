package hse.kpo.dto.responses;

import hse.kpo.dto.AccountDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountResponse {

    private final boolean success;

    private final AccountDto operation;

    private final String message;
}
