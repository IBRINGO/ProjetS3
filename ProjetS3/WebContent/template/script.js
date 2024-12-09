// Get the current page URL
const currentPage = window.location.pathname;

// Get the left-side container
const leftSide = document.querySelector(".left-side");

// Change the background image based on the page
if (currentPage.includes("signup.html")) {
    leftSide.style.background = 'url("signup.jpg")';
    leftSide.style.backgroundSize = 'cover'; // Ensure proper sizing
    leftSide.style.backgroundPosition = 'center'; // Center the image
} else if (currentPage.includes("recruiter.html")) {
    leftSide.style.background = 'url("recruit.jpg")';
    leftSide.style.backgroundSize = 'cover'; // Ensure proper sizing
    leftSide.style.backgroundPosition = 'center'; // Center the image
} else {
    // Default background if needed
    leftSide.style.background = 'url("default.jpg")';
    leftSide.style.backgroundSize = 'cover';
    leftSide.style.backgroundPosition = 'center';
}
