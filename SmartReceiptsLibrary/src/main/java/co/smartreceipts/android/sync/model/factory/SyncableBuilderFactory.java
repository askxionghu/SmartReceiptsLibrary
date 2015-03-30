package co.smartreceipts.android.sync.model.factory;

import android.support.annotation.NonNull;

import java.util.Date;

import co.smartreceipts.android.model.factory.BuilderFactory;
import co.smartreceipts.android.sync.model.Identifier;
import co.smartreceipts.android.sync.model.impl.UniqueId;
import co.smartreceipts.android.sync.model.SyncSource;
import co.smartreceipts.android.sync.model.SyncStatus;
import co.smartreceipts.android.sync.model.SyncMetadata;
import co.smartreceipts.android.sync.model.impl.ImmutableSyncMetadataImpl;

/**
 * <p>
 * A {@link co.smartreceipts.android.sync.model.SyncMetadata} {@link co.smartreceipts.android.model.factory.BuilderFactory}
 * implementation, which will be used to generate instances of {@link co.smartreceipts.android.sync.model.SyncMetadata} objects.
 * <p/>
 * <p>
 * If both a creation and last modification date are not defined via {@link #setCreationDate(java.util.Date)} and
 * {@link #setLastModificationDate(java.util.Date)} (or the {@code long} equivalents), the {@link #build()} will always
 * return {{@link co.smartreceipts.android.sync.model.SyncMetadata#NEVER_SYNCED}}.
 * </p>
 */
public final class SyncableBuilderFactory implements BuilderFactory<SyncMetadata> {

    private Identifier mIdentifier;
    private SyncSource mSource;
    private SyncStatus mStatus;
    private Date mCreationDate;
    private Date mLastModificationDate;

    public SyncableBuilderFactory setIdentifier(@NonNull Identifier identifier) {
        mIdentifier = identifier;
        return this;
    }

    public SyncableBuilderFactory setIdentifier(@NonNull String identifier) {
        mIdentifier = new UniqueId(identifier);
        return this;
    }

    public SyncableBuilderFactory setSource(@NonNull SyncSource syncSource) {
        mSource = syncSource;
        return this;
    }

    public SyncableBuilderFactory setSource(@NonNull String syncSource) {
        mSource = SyncSource.getSourceFromValue(syncSource);
        return this;
    }

    public SyncableBuilderFactory setStatus(@NonNull SyncStatus syncStatus) {
        mStatus = syncStatus;
        return this;
    }

    public SyncableBuilderFactory setStatus(@NonNull String syncStatus) {
        mStatus = SyncStatus.getStatusFromValue(syncStatus);
        return this;
    }

    public SyncableBuilderFactory setCreationDate(Date creationDate) {
        mCreationDate = creationDate;
        return this;
    }

    public SyncableBuilderFactory setCreationDate(long creationDateTime) {
        mCreationDate = new Date(creationDateTime);
        return this;
    }

    public SyncableBuilderFactory setLastModificationDate(Date lastModificationDate) {
        mLastModificationDate = lastModificationDate;
        return this;
    }

    public SyncableBuilderFactory setLastModificationDate(long lastModificationDateTime) {
        mLastModificationDate = new Date(lastModificationDateTime);
        return this;
    }

    @NonNull
    @Override
    public SyncMetadata build() {
        if (mCreationDate == null || mLastModificationDate == null) {
            return SyncMetadata.NEVER_SYNCED;
        } else {
            if (mIdentifier != null && mSource != null && mStatus != null) {
                return new ImmutableSyncMetadataImpl(mIdentifier, mSource, mStatus, mCreationDate, mLastModificationDate);
            } else {
                throw new IllegalArgumentException("Must defined an identifier, status, and source");
            }
        }
    }
}