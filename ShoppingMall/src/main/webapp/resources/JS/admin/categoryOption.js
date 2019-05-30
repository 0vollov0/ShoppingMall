function selectBoxTrriger(){
		$('select[name=category] option').remove();
		switch (Number.parseInt($('select[name=category-first]').val())) {
		case 100:
			appendOption(101,'한손검');
			appendOption(102,'두손검');
			appendOption(103,'도끼');
			break;
		case 200:
			appendOption(201,'나무방패');
			appendOption(202,'철제방패');
			break;
		case 300:
			appendOption(301,'철제투구');
			appendOption(302,'가죽투구');
			appendOption(303,'천모자');
			break;
		case 400:
			appendOption(401,'철제갑옷');
			appendOption(402,'가죽갑옷');
			break;
		case 500:
			appendOption(501,'철제신발');
			appendOption(502,'가죽신발');
			break;
		case 600:
			appendOption(601,'철제장갑');
			break;
		default:
			console.log('default');
			break;
		
		}
	}