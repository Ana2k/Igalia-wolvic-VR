#pragma once

#include <EGL/egl.h>
#include <jni.h>
#include <openxr/openxr.h>
#include <openxr/openxr_platform.h>
#include <vector>
#include <string>
#include <unordered_set>

namespace crow {
  class OpenXRExtensions {
  public:
    static void Initialize();
    static void LoadExtensions(XrInstance instance);
    static bool IsExtensionSupported(const char*);
    static void LoadApiLayers(XrInstance instance);
    static bool IsApiLayerSupported(const char*);

    static PFN_xrGetOpenGLESGraphicsRequirementsKHR sXrGetOpenGLESGraphicsRequirementsKHR;
    static PFN_xrCreateSwapchainAndroidSurfaceKHR sXrCreateSwapchainAndroidSurfaceKHR;

    // hand tracking extension prototypes
    static PFN_xrCreateHandTrackerEXT sXrCreateHandTrackerEXT;
    static PFN_xrDestroyHandTrackerEXT sXrDestroyHandTrackerEXT;
    static PFN_xrLocateHandJointsEXT sXrLocateHandJointsEXT;
    static PFN_xrGetHandMeshFB sXrGetHandMeshFB;

    static PFN_xrPerfSettingsSetPerformanceLevelEXT sXrPerfSettingsSetPerformanceLevelEXT;
    static PFN_xrEnumerateDisplayRefreshRatesFB sXrEnumerateDisplayRefreshRatesFB;
    static PFN_xrRequestDisplayRefreshRateFB sXrRequestDisplayRefreshRateFB;
<<<<<<< HEAD
=======

    static PFN_xrCreatePassthroughFB sXrCreatePassthroughFB;
    static PFN_xrDestroyPassthroughFB sXrDestroyPassthroughFB;
    static PFN_xrCreatePassthroughLayerFB sXrCreatePassthroughLayerFB;
    static PFN_xrDestroyPassthroughLayerFB sXrDestroyPassthroughLayerFB;
>>>>>>> a875aeafd5aca1e9da365dbec648a515ab7c75b9
  private:
     static std::unordered_set<std::string> sSupportedExtensions;
     static std::unordered_set<std::string> sSupportedApiLayers;
  };
} // namespace crow