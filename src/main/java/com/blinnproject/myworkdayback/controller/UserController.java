package com.blinnproject.myworkdayback.controller;

import com.blinnproject.myworkdayback.payload.response.GenericResponse;
import com.blinnproject.myworkdayback.security.UserDetailsImpl;
import com.blinnproject.myworkdayback.service.i18n.I18nService;
import com.blinnproject.myworkdayback.service.profile_picture.ProfilePictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/user/")
public class UserController {

  private final I18nService i18n;
  private final ProfilePictureService profilePictureService;

  public UserController(I18nService i18nService, ProfilePictureService profilePictureService) {
    this.i18n = i18nService;
    this.profilePictureService = profilePictureService;
  }

  @PostMapping("profile-picture")
  public ResponseEntity<GenericResponse<String>> uploadProfilePicture(
      @RequestParam("picture") MultipartFile file,
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) throws IOException {
    String fileName = this.profilePictureService.uploadProfilePicture(file, userDetails.getId());

    return ResponseEntity.ok(GenericResponse.success(fileName, i18n.translate("controller.user.upload-profile-picture.successful")));
  }

  @GetMapping("{username}/profile-picture")
  public ResponseEntity<?> getProfilePictureByUsername(@PathVariable("username") String username) {
    byte[] image = this.profilePictureService.getProfilePictureByUsername(username);

    return ResponseEntity.status(HttpStatus.OK)
      .contentType(MediaType.valueOf("image/png"))
      .body(image);
  }
}
