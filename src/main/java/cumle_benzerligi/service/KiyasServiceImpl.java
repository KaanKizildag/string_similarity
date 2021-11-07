/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.service;

import cumle_benzerligi.entity.Kiyas;
import cumle_benzerligi.repository.KiyasRepository;
import java.util.List;

/**
 *
 * @author kaan
 */
public class KiyasServiceImpl implements KiyasService {

    KiyasRepository repo = KiyasRepository.getInstance();

    @Override
    public List<Kiyas> getKiyasOrderByBenzerlikDesc() {
        return repo.getKiyasOrderByBenzerlikDesc();
    }

    @Override
    public void insert(Kiyas kiyas) {
        repo.insert(kiyas);
    }

    @Override
    public void insert(List<Kiyas> kiyaslar) {
        repo.insert(kiyaslar);
    }

}
