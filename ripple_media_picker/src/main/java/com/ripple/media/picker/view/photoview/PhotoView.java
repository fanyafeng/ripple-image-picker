/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.ripple.media.picker.view.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;


import com.ripple.media.picker.view.RippleImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class PhotoView extends RippleImageView implements IPhotoView {

    private PhotoViewAttacher mAttacher;

    private ScaleType mPendingScaleType;

//    DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
//    private CloseableReference<CloseableImage> imageReference = null;

    public PhotoView(Context context) {
        this(context, null);
        init();
    }

    public PhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
        init();
    }

    public PhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        super.setScaleType(ScaleType.MATRIX);
        init();
    }

    protected void init() {
        if (null == mAttacher || null == mAttacher.getImageView()) {
            mAttacher = new PhotoViewAttacher(this);
        }

        if (null != mPendingScaleType) {
            setScaleType(mPendingScaleType);
            mPendingScaleType = null;
        }

//        if (mDraweeHolder == null) {
//            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
//                    .setFadeDuration(300)
////                    .setProgressBarImage(new CustomProgressbarDrawable(this))
//                    .build();
//            mDraweeHolder = DraweeHolder.create(hierarchy, getContext());
//        }
    }


    /**
     * @deprecated use {@link #setRotationTo(float)}
     */
    @Override
    public void setPhotoViewRotation(float rotationDegree) {
        mAttacher.setRotationTo(rotationDegree);
    }

    @Override
    public void setRotationTo(float rotationDegree) {
        mAttacher.setRotationTo(rotationDegree);
    }

    @Override
    public void setRotationBy(float rotationDegree) {
        mAttacher.setRotationBy(rotationDegree);
    }

    @Override
    public boolean canZoom() {
        return mAttacher.canZoom();
    }

    @Override
    public RectF getDisplayRect() {
        return mAttacher.getDisplayRect();
    }

    @Override
    public Matrix getDisplayMatrix() {
        return mAttacher.getDisplayMatrix();
    }

    @Override
    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return mAttacher.setDisplayMatrix(finalRectangle);
    }

    @Override
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override
    public float getMinimumScale() {
        return mAttacher.getMinimumScale();
    }

    @Override
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override
    public float getMediumScale() {
        return mAttacher.getMediumScale();
    }

    @Override
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override
    public float getMaximumScale() {
        return mAttacher.getMaximumScale();
    }

    @Override
    public float getScale() {
        return mAttacher.getScale();
    }

    @Override
    public ScaleType getScaleType() {
        return mAttacher.getScaleType();
    }

    @Override
    public void setAllowParentInterceptOnEdge(boolean allow) {
        mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    @Override
    @Deprecated
    public void setMinScale(float minScale) {
        setMinimumScale(minScale);
    }

    @Override
    public void setMinimumScale(float minimumScale) {
        mAttacher.setMinimumScale(minimumScale);
    }

    @Override
    @Deprecated
    public void setMidScale(float midScale) {
        setMediumScale(midScale);
    }

    @Override
    public void setMediumScale(float mediumScale) {
        mAttacher.setMediumScale(mediumScale);
    }

    @Override
    @Deprecated
    public void setMaxScale(float maxScale) {
        setMaximumScale(maxScale);
    }

    @Override
    public void setMaximumScale(float maximumScale) {
        mAttacher.setMaximumScale(maximumScale);
    }

    @Override
    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        mAttacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    @Override
    // setImageBitmap calls through to this method
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    @Override
    public void setOnMatrixChangeListener(PhotoViewAttacher.OnMatrixChangedListener listener) {
        mAttacher.setOnMatrixChangeListener(listener);
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        mAttacher.setOnLongClickListener(l);
    }

    @Override
    public void setOnPhotoTapListener(PhotoViewAttacher.OnPhotoTapListener listener) {
        mAttacher.setOnPhotoTapListener(listener);
    }

    @Override
    public PhotoViewAttacher.OnPhotoTapListener getOnPhotoTapListener() {
        return mAttacher.getOnPhotoTapListener();
    }

    @Override
    public void setOnViewTapListener(PhotoViewAttacher.OnViewTapListener listener) {
        mAttacher.setOnViewTapListener(listener);
    }

    @Override
    public PhotoViewAttacher.OnViewTapListener getOnViewTapListener() {
        return mAttacher.getOnViewTapListener();
    }

    @Override
    public void setScale(float scale) {
        mAttacher.setScale(scale);
    }

    @Override
    public void setScale(float scale, boolean animate) {
        mAttacher.setScale(scale, animate);
    }

    @Override
    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        mAttacher.setScale(scale, focalX, focalY, animate);
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (null != mAttacher) {
            mAttacher.setScaleType(scaleType);
        } else {
            mPendingScaleType = scaleType;
        }
    }

    @Override
    public void setZoomable(boolean zoomable) {
        mAttacher.setZoomable(zoomable);
    }

    @Override
    public Bitmap getVisibleRectangleBitmap() {
        return mAttacher.getVisibleRectangleBitmap();
    }

    @Override
    public void setZoomTransitionDuration(int milliseconds) {
        mAttacher.setZoomTransitionDuration(milliseconds);
    }

    @Override
    public IPhotoView getIPhotoViewImplementation() {
        return mAttacher;
    }

    @Override
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        mAttacher.setOnDoubleTapListener(newOnDoubleTapListener);
    }

    @Override
    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        mAttacher.cleanup();
//        mDraweeHolder.onDetach();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        init();
//        mDraweeHolder.onAttach();
        super.onAttachedToWindow();
    }

    @Override
    protected boolean verifyDrawable(Drawable dr) {
//        if (dr == mDraweeHolder.getHierarchy().getTopLevelDrawable()) {
//            return true;
//        }
        return super.verifyDrawable(dr);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
//        mDraweeHolder.onDetach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
//        mDraweeHolder.onAttach();
    }

//    public void setImageUri(String url) {
//        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url)).build();
//        ImagePipeline imagePipeline = Fresco.getImagePipeline();
//        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setOldController(mDraweeHolder.getController())
//                .setImageRequest(imageRequest)
//                .setControllerListener(new BaseControllerListener<ImageInfo>() {
//                    @Override
//                    public void onFinalImageSet(String s, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
//                        try {
//                            imageReference = dataSource.getResult();
//                            if (imageReference != null) {
//                                CloseableImage image = imageReference.get();
//                                // do something with the image
//                                if (image != null && image instanceof CloseableStaticBitmap) {
//                                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) image;
//                                    Bitmap bitmap = closeableStaticBitmap.getUnderlyingBitmap();
//                                    if (bitmap != null) {
//                                        //???????????????????????????
////                                        if (bitmap.getHeight() > 2160 || bitmap.getWidth() > 1140) {
//                                        setImageBitmap(comp(bitmap));
//                                        setScaleType(ScaleType.CENTER_CROP);
////                                        } else {
////                                            setImageBitmap(bitmap);
////                                        }
//                                    }
//                                }
//                            }
//                        } finally {
//                            dataSource.close();
//                            CloseableReference.closeSafely(imageReference);
//                        }
//                    }
//                })
//                .setTapToRetryEnabled(true)
//                .build();
//        mDraweeHolder.setController(controller);
//    }

    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1536) {//????????????????????????1M,????????????????????????????????????BitmapFactory.decodeStream????????????
            baos.reset();//??????baos?????????baos
            image.compress(Bitmap.CompressFormat.JPEG, 30, baos);//????????????50%?????????????????????????????????baos???
        } else {
            return image;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //??????????????????????????????options.inJustDecodeBounds ??????true???
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //??????????????????????????????800*480??????????????????????????????????????????
        float hh = 800f;//?????????????????????800f
        float ww = 480f;//?????????????????????480f
        //????????????????????????????????????????????????????????????????????????????????????????????????
        int be = 1;//be=1???????????????
        if (w > h && w > ww) {//???????????????????????????????????????????????????
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//???????????????????????????????????????????????????
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//??????????????????
        //??????????????????????????????????????????options.inJustDecodeBounds ??????false???
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
//        return compressImage(bitmap);//?????????????????????????????????????????????
        return bitmap;
    }

    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//???????????????????????????100????????????????????????????????????????????????baos???
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //?????????????????????????????????????????????100kb,??????????????????
            baos.reset();//??????baos?????????baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//????????????options%?????????????????????????????????baos???
            options -= 10;//???????????????10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//?????????????????????baos?????????ByteArrayInputStream???
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//???ByteArrayInputStream??????????????????
        return bitmap;
    }

//    public void setImageUri(Uri uri, int width, int height) {
//        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
//                .setAutoRotateEnabled(true)
//                .setResizeOptions(new ResizeOptions(width, height))
//                .build();
//        ImagePipeline imagePipeline = Fresco.getImagePipeline();
//        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setOldController(mDraweeHolder.getController())
//                .setImageRequest(imageRequest)
//                .setControllerListener(new BaseControllerListener<ImageInfo>() {
//                    @Override
//                    public void onFinalImageSet(String s, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
//                        try {
//                            imageReference = dataSource.getResult();
//                            if (imageReference != null) {
//                                CloseableImage image = imageReference.get();
//                                if (image != null && image instanceof CloseableStaticBitmap) {
//                                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) image;
//                                    Bitmap bitmap = closeableStaticBitmap.getUnderlyingBitmap();
//                                    if (bitmap != null) {
//                                        setImageBitmap(bitmap);
//                                    }
//                                }
//                            }
//                        } finally {
//                            dataSource.close();
//                            CloseableReference.closeSafely(imageReference);
//                        }
//                    }
//                })
//                .setTapToRetryEnabled(true)
//                .build();
//        mDraweeHolder.setController(controller);
//    }

//    public void setImageUri(String uri, int width, int height) {
//        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
//                .setAutoRotateEnabled(true)
//                .setResizeOptions(new ResizeOptions(width, height))
//                .build();
//        ImagePipeline imagePipeline = Fresco.getImagePipeline();
//        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setOldController(mDraweeHolder.getController())
//                .setImageRequest(imageRequest)
//                .setControllerListener(new BaseControllerListener<ImageInfo>() {
//                    @Override
//                    public void onFinalImageSet(String s, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
//                        try {
//                            imageReference = dataSource.getResult();
//                            if (imageReference != null) {
//                                CloseableImage image = imageReference.get();
//                                if (image != null && image instanceof CloseableStaticBitmap) {
//                                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) image;
//                                    Bitmap bitmap = closeableStaticBitmap.getUnderlyingBitmap();
//                                    if (bitmap != null) {
//                                        setImageBitmap(bitmap);
//                                    }
//                                }
//                            }
//                        } finally {
//                            dataSource.close();
//                            CloseableReference.closeSafely(imageReference);
//                        }
//                    }
//                })
//                .setTapToRetryEnabled(true)
//                .build();
//        mDraweeHolder.setController(controller);
//    }



}