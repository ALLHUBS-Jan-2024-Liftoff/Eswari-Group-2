import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LandingLogin from './components/LoginLandingPage/LoginLanding.jsx';
import SignUpPage from './components/SignUpPage/SignUpPage.jsx';
import SearchPage from './components/SearchPage/SearchPage.jsx'
import Gallery from './components/ArtGallery/Gallery.jsx';
import ProfilePage from './components/ProfilePage/ProfilePage.jsx';
import NotificationPage from './components/NotificationPage/NotificationsPage.jsx';




const router = createBrowserRouter([
  {
    path: '/',
    element: <LandingLogin />
  },
  {
    path:'/signup',
    element: <SignUpPage />
  },
  {path:'/search',
    element: <SearchPage />},
    { 
    path:'/gallery',
    element: <Gallery />
  },
  {
  path:'/notifications',
  element:<NotificationPage />
  },
  {
  path:'/profile',
  element:<ProfilePage />
  },
  // tags, upload artwork and artist's artwork list
  {
    path:'/uploadartwork',
    element: <UploadArtwork />
  },

  {
    path:'/artworklist',
    element: <ArtworkList />
  },

  {
    path:'/tag',
    element: <Tag />
  }

]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <RouterProvider router={router} />
  </React.StrictMode>,
)
