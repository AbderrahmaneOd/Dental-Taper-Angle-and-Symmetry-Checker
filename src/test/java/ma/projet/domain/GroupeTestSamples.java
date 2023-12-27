package ma.projet.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GroupeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Groupe getGroupeSample1() {
        return new Groupe().id(1L).code("code1").annee("annee1");
    }

    public static Groupe getGroupeSample2() {
        return new Groupe().id(2L).code("code2").annee("annee2");
    }

    public static Groupe getGroupeRandomSampleGenerator() {
        return new Groupe().id(longCount.incrementAndGet()).code(UUID.randomUUID().toString()).annee(UUID.randomUUID().toString());
    }
}
