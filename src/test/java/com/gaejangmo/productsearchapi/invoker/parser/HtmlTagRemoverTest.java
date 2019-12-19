package com.gaejangmo.productsearchapi.invoker.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

class HtmlTagRemoverTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "애플 <b>맥북</b>프로 13형 2019년형 MUHN2KH/A",
            "<b>로지텍</b> MK270r Wireless Combo",
            "<b>샤오미</b> 초정밀 온습도계 MHO-C201",
            "[<b>샤오미</b> 총판 정식수입] 치후360 S7 / S6 / S5",
            "바디닥터스 <b>루테인</b> 지아잔틴 500mg x 30캡슐",
            "<b>레노버</b> 아이디어패드 S340-15IWL Platinum",
    })
    void html_태그_필터링(final String input) {
        String result = HtmlTagRemover.parse(input);
        assertFalse(result.contains("<"));
        assertFalse(result.contains(">"));
    }
}