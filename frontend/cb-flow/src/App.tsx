import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from "./shared/components/Header";
import LandingPage from "./shared/pages/LandingPage";
import LoginPage from './features/login/ui/pages/LoginPage';
import SignUpPage from './features/login/ui/pages/SignUpPage';
import OtpPage from './features/login/ui/pages/OtpPage';
import SelectTokenPage from './features/exchange/ui/pages/SelectTokenPage';
import OnOffRampPage from './features/exchange/ui/pages/OnOffRampPage';

function App() {
  return (
    <Router>
      <Header />

      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path='/otp' element={<OtpPage />} />
        <Route path='/select-token' element={<SelectTokenPage />} />
        <Route path='/on-off-ramp' element={<OnOffRampPage />} />
      </Routes>


    </Router>
  );
}

export default App;