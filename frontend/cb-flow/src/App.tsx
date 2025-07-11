import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from "./shared/components/Header";
import LandingPage from "./shared/pages/LandingPage";
import LoginPage from './features/login/ui/pages/LoginPage';
import SignUpPage from './features/login/ui/pages/SignUpPage';
import OtpPage from './features/login/ui/pages/OtpPage';
import SelectTokenPage from './features/exchange/ui/pages/SelectTokenPage';
import OnOffRampPage from './features/exchange/ui/pages/OnOffRampPage';
import ProtectedRoute from './shared/pages/ProtectedRoute';


function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<LandingPage />} />

        {/* Rutas públicas (redirigen si está autenticado) */}
        <Route
          path="/login"
          element={
            <ProtectedRoute requireAuth={false}>
              <LoginPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/otp"
          element={
            <ProtectedRoute requireAuth={false}>
              <OtpPage />
            </ProtectedRoute>
          }
        />

        {/* Rutas privadas */}
        <Route path='/signup'
          element={
            <ProtectedRoute>
              <SignUpPage />
            </ProtectedRoute>
          }
        />
        <Route
          path='/select-token'
          element={
            <ProtectedRoute>
              <SelectTokenPage />
            </ProtectedRoute>
          }
        />
        <Route
          path='/on-off-ramp'
          element={
            <ProtectedRoute>
              <OnOffRampPage />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;