package com.snakerealms.realms;

public class RealmFactoryProducer {

    public static RealmFactory getFactory(String realmType) {
        if (realmType == null) {
            return null;
        }

        switch (realmType.toLowerCase()) {
            case "jungle":
                return new JungleRealmFactory();
            case "space":
                return new SpaceRealmFactory();
            case "ocean":
                return new OceanRealmFactory();
            default:
                throw new IllegalArgumentException("Unknown realm: " + realmType);
        }
    }
}
