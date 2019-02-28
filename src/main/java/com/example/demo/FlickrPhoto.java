package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

public class FlickrPhoto {

	private static final String IMAGE_URL = "https://farm%s.staticflickr.com/%s/%s_%s.jpg";

    public static List<String> main(String[] args) throws FlickrException {
        final Flickr flickr = new Flickr(
                "${Api Key}",
                "${Shared Secret}",
                new REST()
        );

		PhotosetsInterface photosetsInterface = flickr.getPhotosetsInterface();
		Photosets photosets = photosetsInterface.getList(null);
		List<String> ImageUrlList = new ArrayList<String>();

		photosets.getPhotosets().stream().map(Photoset::getId).flatMap(id -> {
			try {
				return photosetsInterface.getPhotos(id, 0, 0).stream();
			} catch (FlickrException e) {
				throw new RuntimeException();
			}
		}).forEach(photo -> ImageUrlList.add(
				String.format(
						IMAGE_URL, photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret())));

		return ImageUrlList;
	}
}
