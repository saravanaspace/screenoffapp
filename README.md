# Screen Off 📱

A simple Android app that turns off your device screen when launched. Perfect for quickly turning off your screen without using the power button.

## 🚀 Features

- **One-tap screen off**: Just tap the app icon and your screen turns off immediately
- **No UI clutter**: Minimal interface - the app does exactly what it says
- **Battery efficient**: Uses Android's built-in screen control APIs
- **Lightweight**: Small app size with minimal permissions
- **Comprehensive testing**: Full unit and integration test coverage

## 🛠️ Built with Cursor

This entire app was developed using [Cursor](https://cursor.sh), the AI-powered code editor. From initial project setup to final testing, Cursor helped with:

- Project structure and architecture
- Android development best practices
- Screen control implementation
- Comprehensive test suite
- Code optimization and cleanup
- Documentation

## 📱 Screenshots

*Note: Since this app turns off the screen immediately, there's no UI to show! The app icon appears as "Screen Off" in your app launcher.*

## 🏗️ Architecture

- **Language**: Kotlin
- **Framework**: Android (Jetpack Compose for minimal UI)
- **Architecture**: MVVM with clean separation of concerns
- **Testing**: JUnit, Robolectric, Espresso
- **Build System**: Gradle with Kotlin DSL

### Core Components

- **MainActivity**: Entry point that immediately turns off the screen
- **ScreenManager**: Utility class handling screen control logic
- **Theme**: Minimal Material 3 theming

## 🔧 Setup & Installation

### Prerequisites

- Android Studio or compatible IDE
- Android SDK (API level 33+)
- Java 17 or higher

### Building from Source

1. **Clone the repository**
   ```bash
   git clone https://github.com/saravanaspace/screen-off-app.git
   cd screen-off-app
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory and select it

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run tests**
   ```bash
   ./gradlew test                    # Unit tests
   ./gradlew connectedAndroidTest    # Instrumented tests
   ```

5. **Install on device**
   ```bash
   ./gradlew installDebug
   ```

## 🧪 Testing

The app includes comprehensive test coverage:

### Unit Tests (12 tests)
- ScreenManager functionality
- Error handling and edge cases
- Resource cleanup
- Multiple instance handling

### Integration Tests (6 tests)
- MainActivity lifecycle
- Complete app flow
- Multiple launch scenarios

### Running Tests
```bash
# All tests
./gradlew check

# Unit tests only
./gradlew test

# Instrumented tests only
./gradlew connectedAndroidTest
```

## 🔒 Permissions

The app requires minimal permissions:
- `WAKE_LOCK`: Required to control screen state

## 📋 Requirements

- **Minimum SDK**: API 33 (Android 13)
- **Target SDK**: API 35 (Android 15)
- **Java Version**: 17

## 🚀 Usage

1. Install the app on your Android device
2. Find "Screen Off" in your app launcher
3. Tap the app icon
4. Your screen will turn off immediately

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Development Setup
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Built entirely with [Cursor](https://cursor.sh) - the AI-powered code editor
- Android development community for best practices
- Material Design for UI guidelines

## 📞 Support

If you encounter any issues or have questions:
- Open an issue on GitHub
- Check the existing issues for solutions

## Recent Changes

- Switched the launcher icon to a custom PNG image, as provided by the user.
- Cleaned up unused adaptive icon XML/vector files and related foreground/background assets.
- Removed all unused icon files, keeping only the PNGs generated for each density.
- **Launcher icon generated using [Android Asset Studio - Launcher Icon Generator](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html)** for best compatibility and appearance on all Android devices.

## How to Update the Icon

1. Use the [Launcher Icon Generator](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html) to create all required PNG sizes from your image.
2. Replace the PNGs in `app/src/main/res/mipmap-*dpi/` with the generated files.
3. Ensure your `AndroidManifest.xml` references `@mipmap/ic_launcher` as the app icon.

## Credits
- Launcher icon generated with [Android Asset Studio - Launcher Icon Generator](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html)
- User-provided image for the icon design

## Android Security Restrictions & Limitations

**Important:** Due to Android security policies, there are significant restrictions on what third-party apps can do regarding screen-off and device locking, especially on modern devices (Pixel, Samsung, etc.) and recent Android versions (Android 12+ and especially Android 14/15/16):

- **Device Admin lock**: Will always require PIN/pattern/password after lock (biometrics are disabled for the next unlock). This is enforced by Android for security and cannot be bypassed.
- **Accessibility Service lock**: Increasingly blocked by the system and Play Protect for non-accessibility use cases. On Android 14+ and especially Android 16 (U), you may see "Access Denied" or the service may not stay enabled.
- **Play Protect**: May block or warn about apps using Accessibility or Device Admin for screen-off, especially if sideloaded or not from the Play Store.
- **Root required for true power button simulation**: Only possible on rooted devices (not recommended for most users).
- **Overlay/Fake Off**: Apps can overlay a black screen, but this does not actually turn off or lock the device and is not secure.
- **Physical Power Button**: The only 100% reliable and secure way to turn off the screen and allow biometric unlock on all Android versions.

### Summary Table

| Method                | Works on Modern Android? | Allows Biometrics? | Play Store Allowed? | Notes                        |
|-----------------------|:-----------------------:|:------------------:|:-------------------:|------------------------------|
| Power Button          | Yes                     | Yes                | Yes                 | Physical only                |
| Device Admin          | Yes                     | No                 | Yes                 | PIN required after lock      |
| Accessibility Service | No (blocked)            | Yes                | No                  | Blocked by system/Play Protect|
| Root                  | Yes (if rooted)         | Yes                | No                  | Not for most users           |
| Overlay/Fake Off      | Yes                     | N/A                | Yes                 | Not secure, not a real lock  |

**If you see Play Protect warnings, "Access Denied" in Accessibility, or are forced to enter your PIN after locking, these are system-enforced and cannot be bypassed by any app.**

For more details, see the [Android developer documentation](https://developer.android.com/guide/topics/admin/device-admin) and [Play Store policies](https://support.google.com/googleplay/android-developer/answer/9047303).

---

**Made with ❤️ and Cursor** 