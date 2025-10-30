package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public List<Lotto> createLottoBundle(int purchaseMoney) {
        int lottoCount = purchaseMoney / 1000;
        List<Lotto> lottoBundle = new ArrayList<>(lottoCount);
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottoBundle.add(new Lotto(numbers));
        }
        return lottoBundle;
    }
}
