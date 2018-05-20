package daveho.co.auntypasty.mastdata.modules;

import daveho.co.auntypasty.mastdata.repository.MastDataRepository;

/**
 * Module to allow the repository instance to be retrieved from elsewhere
 * in the codebase.
 */
public class MastDataRepositoryModule {

    private static MastDataRepository mMastDataRepository;

    public static MastDataRepository mastDataRepository() {
        if (mMastDataRepository == null) {
            synchronized (MastDataRepository.class) {
                if (mMastDataRepository == null) {
                    mMastDataRepository = new MastDataRepository();
                }
            }
        }

        return mMastDataRepository;
    }
}
