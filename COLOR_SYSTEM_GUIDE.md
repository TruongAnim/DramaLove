# 🎨 DramaLove Color System Guide

## ✅ Hoàn thành Color System

### 🎯 **Đã tạo thành công:**

1. **Bộ màu hoàn chỉnh** trong `Color.kt`
2. **Theme Material 3** trong `Theme.kt`
3. **Extension functions** trong `ThemeExtensions.kt`
4. **Áp dụng vào Splash Screen** với theme colors

---

## 🎨 **DramaLove Color Palette**

### **Primary Purple Palette**
```kotlin
Purple900 = #4A148C  // Darkest
Purple800 = #6A1B9A  // Brand Primary Dark
Purple700 = #7B1FA2  // Brand Primary
Purple600 = #8E24AA  // Brand Primary Light
Purple500 = #9C27B0  // Material Purple
Purple400 = #AB47BC  // Light
Purple300 = #BA68C8  // Lighter
Purple200 = #CE93D8  // Very Light
Purple100 = #E1BEE7  // Pale
Purple50  = #F3E5F5  // Background
```

### **Secondary Pink Palette**
```kotlin
Pink900 = #880E4F   // Darkest
Pink800 = #AD1457   // Dark
Pink700 = #C2185B   // Primary
Pink600 = #D81B60   // Medium
Pink500 = #E91E63   // Material Pink
Pink400 = #EC407A   // Light
Pink300 = #F06292   // Lighter
Pink200 = #F48FB1   // Very Light
Pink100 = #F8BBD9   // Pale
Pink50  = #FCE4EC   // Background
```

### **Accent Colors**
```kotlin
Orange500 = #FF9800  // Premium/Favorite
Blue500   = #2196F3  // Info/Share
Success   = #4CAF50  // Success states
Warning   = #FF9800  // Warning states
Error     = #F44336  // Error states
```

---

## 🎭 **Material 3 Theme Integration**

### **Light Theme**
- **Primary**: Purple700 (#7B1FA2)
- **Secondary**: Pink700 (#C2185B)
- **Tertiary**: Orange500 (#FF9800)
- **Background**: White
- **Surface**: White
- **Error**: Red (#F44336)

### **Dark Theme**
- **Primary**: Purple400 (#AB47BC)
- **Secondary**: Pink400 (#EC407A)
- **Tertiary**: Orange400 (#FFA726)
- **Background**: Grey900 (#212121)
- **Surface**: Grey800 (#424242)
- **Error**: Red variant

---

## 🚀 **Gradient System**

### **Available Gradients**
```kotlin
DramaLoveGradients.splashGradient()    // Purple gradient for splash
DramaLoveGradients.primaryGradient()   // Primary brand gradient
DramaLoveGradients.secondaryGradient() // Secondary gradient
DramaLoveGradients.cardGradient()      // Card backgrounds
DramaLoveGradients.shimmerGradient()   // Loading animations
```

---

## 🎯 **Semantic Colors**

### **Video Player**
```kotlin
colorScheme.videoPlayerBackground  // Black
colorScheme.videoPlayerControls    // White with alpha
colorScheme.videoPlayerProgress    // Purple500
```

### **Social Interactions**
```kotlin
colorScheme.likeColor     // Pink500
colorScheme.shareColor    // Blue500
colorScheme.favoriteColor // Orange500
```

### **Status Colors**
```kotlin
colorScheme.success       // Green
colorScheme.warning       // Orange
colorScheme.error         // Red
colorScheme.info          // Blue
colorScheme.onlineStatus  // Green
colorScheme.offlineStatus // Grey
colorScheme.premiumColor  // Orange
```

---

## 📱 **Usage Examples**

### **Using Theme Colors**
```kotlin
@Composable
fun MyComponent() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = "Hello",
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
```

### **Using Brand Colors**
```kotlin
@Composable
fun BrandButton() {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = DramaLoveColors.Purple700
        )
    ) {
        Text("Click me")
    }
}
```

### **Using Gradients**
```kotlin
@Composable
fun GradientBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DramaLoveGradients.splashGradient())
    )
}
```

### **Using Semantic Colors**
```kotlin
@Composable
fun LikeButton() {
    IconButton(onClick = { }) {
        Icon(
            Icons.Default.Favorite,
            tint = MaterialTheme.colorScheme.likeColor
        )
    }
}
```

---

## 🎨 **Design Guidelines**

### **Do's**
- ✅ Use `MaterialTheme.colorScheme` for standard UI elements
- ✅ Use `DramaLoveColors` for brand-specific elements
- ✅ Use semantic colors for consistent meaning
- ✅ Use gradients for special backgrounds
- ✅ Support both light and dark themes

### **Don'ts**
- ❌ Don't hardcode color values
- ❌ Don't use colors that don't contrast well
- ❌ Don't mix different color systems
- ❌ Don't ignore accessibility guidelines

---

## 🚀 **Next Steps**

1. **Apply to all screens** - Use theme colors consistently
2. **Add more gradients** - Create specific gradients for different UI patterns
3. **Test accessibility** - Ensure proper contrast ratios
4. **Create color variants** - Add hover, pressed, disabled states
5. **Document usage** - Create style guide for team

---

## 🎉 **Result**

✅ **Complete color system ready for use**
✅ **Material 3 theme integration**
✅ **Gradient system for beautiful UI**
✅ **Semantic colors for consistency**
✅ **Applied to Splash Screen successfully**

**Your app now has a professional, consistent color system!** 🎨
