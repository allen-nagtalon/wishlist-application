import { Box, ButtonBase, styled } from '@mui/material'
import { useState } from 'react'
import defaultGiftIcon from '../../assets/default-gift.png'

const Image = styled('img')({
  objectFit: 'cover',
  objectPosition: 'center',
  width: 250,
  height: 250
})

const VisuallyHiddenInput = styled('input')({
  clip: 'rect(0 0 0 0)',
  clipPath: 'inset(50%)',
  height: 1,
  overflow: 'hidden',
  position: 'absolute',
  bottom: 0,
  left: 0,
  whiteSpace: 'nowrap',
  width: 1
})

const ImageUpload = (props) => {
  const [imageUrl, setImageUrl] = useState(defaultGiftIcon)

  const handleUploadClick = (event) => {
    console.log(event)

    if (event.target.files.length > 0) {
      const file = event.target.files[0]
      const reader = new FileReader()
      reader.readAsDataURL(file)

      reader.onloadend = (e) => {
        setImageUrl(e.target.result)
      }

      props.setImageFile(file)
    }
  }

  return (
    <>
      <Box sx={{ flex: 1 }}>
        <ButtonBase
          component='label'
          role={undefined}
          tabIndex={-1}
        >
          <VisuallyHiddenInput
            type='file'
            onChange={handleUploadClick}
          />
          <Image src={imageUrl} />
        </ButtonBase>
      </Box>
    </>
  )
}

export default ImageUpload
