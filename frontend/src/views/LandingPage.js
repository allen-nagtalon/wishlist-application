import { Box, Button, Typography } from '@mui/material'
import bg from '../assets/landing-bg.jpg'

function LandingPage () {
  return (
    <>
      <Box
        id='bg-img'
        sx={{
          zIndex: -1,
          backgroundImage: `url(${bg})`,
          backgroundSize: 'cover',
          backgroundAttachment: 'fixed',
          height: '100vh'
        }}
      >
        <Box
          id='hero'
          sx={{
            height: '1000px',
            px: 10,
            zIndex: 1,
            alignContent: 'center'
          }}
        >
          <Typography
            variant='h2'
            sx={{
              fontWeight: 700,
              color: 'text.light'
            }}
          >
            The gift of giving – made easy
          </Typography>
          <Typography
            variant='h4'
            sx={{
              py: 3,
              color: 'text.light'
            }}
          >
            Create your wishlist today!
          </Typography>
          <Button
            component='a'
            href='/register'
            sx={{
              px: 3,
              py: 1,
              bgcolor: 'accent.main',
              color: 'text.light'
            }}
          >
            REGISTER NOW
          </Button>
        </Box>
        <Box
          id='about'
          sx={{
            height: '600px',
            bgcolor: 'light.main'
          }}
        >
          Test
        </Box>
      </Box>
    </>
  )
}

export default LandingPage
